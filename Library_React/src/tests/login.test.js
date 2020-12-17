import { shallow, configure } from 'enzyme';
import Login from '../components/login';
import { render, screen } from '@testing-library/react';
import Adapter from 'enzyme-adapter-react-16';
configure({adapter: new Adapter()});

test('login', () => {
    const component = shallow(<Login/>);
    const component1 = component.find('.login')
    expect(component1.length).toBe(1);
})


  
 