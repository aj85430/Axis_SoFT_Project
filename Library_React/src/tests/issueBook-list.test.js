import { shallow, configure } from 'enzyme';
import IssueBook from '../components/issueBook-list';
import { render, screen } from '@testing-library/react';
import Adapter from 'enzyme-adapter-react-16';
configure({adapter: new Adapter()});

test('issuebook-list', () => {
    const component = shallow(<IssueBook/>);
    const component1 = component.find('.issuebooklist')
    expect(component1.length).toBe(1);
})


  
 