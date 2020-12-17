import { shallow, configure } from 'enzyme';
import UserList from '../components/user-list';
import { render, screen } from '@testing-library/react';
import Adapter from 'enzyme-adapter-react-16';
configure({adapter: new Adapter()});

test('user-list', () => {
    const component = shallow(<UserList/>);
    const component1 = component.find('.userlist')
    expect(component1.length).toBe(1);
})


  
 